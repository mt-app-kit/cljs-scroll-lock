
(ns scroll-lock.side-effects
    (:require [dom.api           :as dom]
              [fruits.css.api    :as css]
              [fruits.math.api   :as math]
              [fruits.string.api :as string]
              [scroll-lock.env   :as env]
              [common-state.api :as common-state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn disable-dom-scroll!
  ; @ignore
  []
  ; - Sets the 'overflow-y' property of the HTML element to 'hidden'.
  ; - Sets the 'position' property of the BODY element to 'fixed'.
  ; - Sets the 'width' property of the BODY element to '100%' (to avoid collapsing).
  ; - Moves the BODY element (BTT) based on the last scroll Y value (Y axis offset).
  (let [scroll-y            (dom/get-scroll-y)
        body-offset-y       (math/negative scroll-y)
        body-style          {:position :fixed :width :100% :top (css/px body-offset-y)}
        document-style      {:overflow-y :hidden}
        document-attributes {:data-scroll-locked true}]
       (dom/merge-element-inline-style! (dom/get-document-element) document-style)
       (dom/merge-element-inline-style! (dom/get-body-element)     body-style)
       (dom/merge-element-attributes!   (dom/get-document-element) document-attributes)))

(defn enable-dom-scroll!
  ; @ignore
  []
  ; - Removes the 'overflow-y' property of the HTML element.
  ; - Removes the 'position' property of the BODY element.
  ; - Removes the 'width' property of the BODY element.
  ; - Removes the 'top' property of the BODY element.
  (if (env/dom-scroll-disabled?)
      (let [body-top (dom/get-body-inline-style-value :top)
            scroll-y (-> body-top string/to-integer math/positive)]
           (dom/remove-element-attribute!          (dom/get-document-element) :data-scroll-locked)
           (dom/remove-element-inline-style-value! (dom/get-document-element) :overflow-y)
           (dom/remove-element-inline-style-value! (dom/get-body-element)     :position)
           (dom/remove-element-inline-style-value! (dom/get-body-element)     :width)
           (dom/remove-element-inline-style-value! (dom/get-body-element)     :top)
           (dom/set-scroll-y! scroll-y))))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn enable-scroll!
  ; @description
  ; Re-enables scrolling on the HTML element.
  ;
  ; @usage
  ; (enable-scroll!)
  []
  (common-state/dissoc-state! :scroll-lock :prohibitions)
  (enable-dom-scroll!))

(defn disable-scroll!
  ; @description
  ; Disables scrolling on the HTML element.
  ;
  ; @usage
  ; (disable-scroll!)
  []
  (when-not (env/any-scroll-prohibition-added?)
            (common-state/assoc-state! :scroll-lock :prohibitions ::default true)
            (disable-dom-scroll!)))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn add-scroll-prohibition!
  ; @description
  ; Disables scrolling on the HTML element.
  ;
  ; @param (keyword) prohibition-id
  ;
  ; @usage
  ; (add-scroll-prohibition! :my-prohibition)
  [prohibition-id]
  (common-state/assoc-state! :scroll-lock :prohibitions prohibition-id true)
  (if-not (env/any-scroll-prohibition-added?)
          (disable-dom-scroll!)))

(defn remove-scroll-prohibition!
  ; @description
  ; Removes a specific scroll prohibition.
  ;
  ; @param (keyword) prohibition-id
  ;
  ; @usage
  ; (remove-scroll-prohibition! :my-prohibition)
  [prohibition-id]
  (when (env/scroll-prohibition-added? prohibition-id)
        (common-state/dissoc-state! :scroll-lock :prohibitions prohibition-id)
        (if-not (env/any-scroll-prohibition-added?)
                (enable-dom-scroll!))))

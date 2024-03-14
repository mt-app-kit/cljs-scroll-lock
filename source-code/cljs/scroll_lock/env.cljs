
(ns scroll-lock.env
    (:require [dom.api :as dom]
              [common-state.api :as common-state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn dom-scroll-disabled?
  ; @ignore
  ;
  ; @return (boolean)
  []
  (-> (dom/get-document-element)
      (dom/get-element-attribute :data-scroll-locked)
      (= "true")))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn any-scroll-prohibition-added?
  ; @ignore
  ;
  ; @return (boolean)
  []
  (-> (common-state/get-state :scroll-lock :prohibitions) empty? not))

(defn scroll-prohibition-added?
  ; @ignore
  ;
  ; @param (keyword) prohibition-id
  ;
  ; @return (boolean)
  [prohibition-id]
  (-> (common-state/get-state :scroll-lock :prohibitions)
      (get prohibition-id)))

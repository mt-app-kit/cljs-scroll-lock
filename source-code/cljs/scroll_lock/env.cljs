
(ns scroll-lock.env
    (:require [dom.api           :as dom]
              [scroll-lock.state :as state]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn dom-scroll-disabled?
  ; @ignore
  ;
  ; @return (boolean)
  []
  (= "true" (dom/get-element-attribute (dom/get-document-element) "data-scroll-locked")))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

(defn any-scroll-prohibition-added?
  ; @ignore
  ;
  ; @return (boolean)
  []
  (-> @state/PROHIBITIONS empty? not))

(defn scroll-prohibition-added?
  ; @ignore
  ;
  ; @param (keyword) prohibition-id
  ;
  ; @return (boolean)
  [prohibition-id]
  (prohibition-id @state/PROHIBITIONS))

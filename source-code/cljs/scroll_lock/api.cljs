
(ns scroll-lock.api
    (:require [scroll-lock.side-effects :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; scroll-lock.side-effects
(def enable-scroll!             side-effects/enable-scroll!)
(def disable-scroll!            side-effects/disable-scroll!)
(def add-scroll-prohibition!    side-effects/add-scroll-prohibition!)
(def remove-scroll-prohibition! side-effects/remove-scroll-prohibition!)

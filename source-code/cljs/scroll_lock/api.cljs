
(ns scroll-lock.api
    (:require [scroll-lock.side-effects :as side-effects]))

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @tutorial Demo
;
; @usage
; ;; Disables scrolling on the HTML element.
; (disable-scroll!)
;
; ;; Re-enables scrolling on the HTML element.
; (enable-scroll!)
;
; ;; Registers a scroll prohibition that disables scrolling on the HTML element.
; (add-scroll-prohibition! :my-prohibition)
;
; ;; Removes a the registered scroll prohibition and re-enables scrolling if not other prohibition is added.
; (remove-scroll-prohibition! :my-prohibition)

;; ----------------------------------------------------------------------------
;; ----------------------------------------------------------------------------

; @redirect (scroll-lock.side-effects/*)
(def enable-scroll!             side-effects/enable-scroll!)
(def disable-scroll!            side-effects/disable-scroll!)
(def add-scroll-prohibition!    side-effects/add-scroll-prohibition!)
(def remove-scroll-prohibition! side-effects/remove-scroll-prohibition!)

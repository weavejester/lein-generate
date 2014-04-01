(ns leiningen.generate.namespace
  (:refer-clojure :exclude [namespace])
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "namespace"))

(defn namespace
  "Generate a new namespace."
  [name]
  (let [data {:namespace name, :sanitized (name-to-path name)}]
    (main/info "Generating src/{{sanitized}}.clj")
    (->files data ["src/{{sanitized}}.clj" (render "source.clj" data)])))

(ns leiningen.generate.namespace
  (:refer-clojure :exclude [namespace])
  (:require [leiningen.generate.templates :as tmpl]
            [leiningen.new.templates :refer [name-to-path]]
            [leiningen.core.main :as main]))

(defn namespace
  "Generate a new namespace and test file."
  [project name]
  (let [data {:name name, :sanitized (name-to-path name)}]
    (doto (tmpl/renderer "namespace")
      (tmpl/create-file "source.clj" "src/{{sanitized}}.clj" data)
      (tmpl/create-file "test.clj" "test/{{sanitized}}_test.clj" data))))

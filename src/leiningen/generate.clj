(ns leiningen.generate
  (:require [leiningen.core.project :as project]
            [leiningen.core.main :refer [abort]]))

(defn resolve-generator [name]
  (let [sym (symbol (str "leiningen.generate." name))]
    (if (try (require sym) true
             (catch java.io.FileNotFoundException _ false))
      (resolve (symbol (str sym "/" name)))
      (abort "Could not find template" name "on the classpath."))))

(defn generate
  "Generate files from a template."
  [project template & args]
  (project/load-plugins project :generators)
  (apply (resolve-generator template) args))

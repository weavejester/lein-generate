(ns leiningen.generate
  (:require [leiningen.core.project :as project]
            [leiningen.core.main :refer [abort parse-options option-arg]]
            [bultitude.core :as bultitude]))

(defn list-generators []
  (for [n (bultitude/namespaces-on-classpath :prefix "leiningen.generate.")
        :when (not= n 'leiningen.generate.templates)]
    (-> (doto n require)
        (the-ns)
        (ns-resolve (symbol (last (.split (str n) "\\.")))))))

(defn print-generators [generators]
  (println "Subtasks available:")
  (doseq [g generators]
    (println (.sym g) "\t" (-> g meta :doc))))

(defn resolve-generator [name]
  (let [sym (symbol (str "leiningen.generate." name))]
    (if (try (require sym) true
             (catch java.io.FileNotFoundException _ false))
      (resolve (symbol (str sym "/" name)))
      (abort "Could not find template" name "on the classpath."))))

(defn run-generator [[template & args]]
  (apply (resolve-generator template) args))

(defn generate
  "Generate files from a template."
  [project & args]
  (let [[options args] (parse-options args)]
    (project/load-plugins project :generators)
    (cond
     (:list options) (print-generators (list-generators))
     :else           (run-generator args))))

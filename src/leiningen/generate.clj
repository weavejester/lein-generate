(ns leiningen.generate
  (:require [leiningen.core.project :as project]
            [leiningen.core.main :refer [abort parse-options option-arg]]
            [leiningen.help :as help]
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
  "Generate scaffolding in an existing project.

A list of generators can be found with:

    lein generate :list

Generators can be run with:

    lein generate $GENERATOR_NAME

Often generators will require arguments:

    lein generate $GENERATOR_NAME $ARG1 $ARG2"
  {:help-arglists '[[project template & args]]}
  [project & args]
  (let [[options args] (parse-options args)]
    (cond
     (:list options)   (print-generators (list-generators))
     (:--help options) (help/help "generate")
     :else             (run-generator args))))

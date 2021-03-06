(defproject lein-gen/generators "0.2.1"
  :description "A basic set of generators for lein-gen"
  :url "https://github.com/weavejester/lein-gen"
  :scm {:dir ".."}
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :dependencies [[lein-gen "0.2.1"]]
  :profiles {:dev {:plugins [[lein-gen "0.2.1"]]}})

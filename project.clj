(defproject lein-gen "0.1.0"
  :description "Lein plugin for generating files"
  :url "https://github.com/weavejester/lein-gen"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :eval-in-leiningen true
  :profiles
  {:dev {:generators [[lein-gen/generators "0.1.0"]]}})

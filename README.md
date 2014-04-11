# lein-gen(erate)

A [Leiningen][1] plugin for generating file templates within an existing
project.

[1]: https://github.com/technomancy/leiningen

## Usage

Add `lein-gen` as a plugin dependency to your project or profiles.

```clojure
:plugins [[lein-gen "0.1.0"]]
```

Then add dependencies for the generators you want to use. This plugin
comes with an optional library of simple generators:

```clojure
:generators [[lein-gen/generators "0.1.0"]]
```

Included in this library is a namespace generator, which creates a new
source and test file for a given namespace:

```
lein generate namespace bar.core
```

The above example will create two new files: `src/bar/core.clj` and
`test/bar/core_test.clj`.

To find out the generators you have available, use:

```
lein help generate
```

For help on a specific generator, such as the namespace generator,
use:

```
lein help generate namespace
```

Generators are very similar to Leiningen project templates in both
function and the way they are developed. To create your own library of
generators, start with:

```
lein new generators foo
```

Note the plural of "generators". Unlike templates, there can be many
generators in a library.

## License

Copyright © 2014 James Reeves

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

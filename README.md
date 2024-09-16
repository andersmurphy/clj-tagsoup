This fork removes dependency on `stax-utils` and `clojure.data.xml` as it only supports converting html/xml to hiccup format. I wanted a lightweight, low dependency html parser that outputs hiccup and doesn't come with a predefined html query mechanism.

clj-tagsoup
===========

This is a HTML parser for Clojure, somewhat akin to Common Lisp's
[cl-html-parse].  It is a wrapper around the [TagSoup] Java SAX
parser, but has a DOM interface.

Usage
-----

The two main functions defined by clj-tagsoup are `parse` and `parse-string`.
The first one can take anything accepted by clojure.java.io's [reader] function
except for a `Reader`,
while the second can parse HTML from a string.

The resulting HTML tree is a vector, consisting of:

 1. a keyword representing the tag name,
 2. a map of tag attributes (mapping keywords to strings),
 3. children nodes (strings or vectors of the same format).

This is the same format as used by [hiccup], thus the output of `parse` is
appropriate to pass to hiccup.

There are also utility accessors (`tag`, `attributes`, `children`).

clj-tagsoup will automatically use the correct encoding to parse the file if
one is specified in either the HTTP headers (if the argument to `parse` is an
URL object or a string representing one) or a `<meta http-equiv="...">` tag.

clj-tagsoup is meant to parse HTML tag soup, but, in practice, nothing
prevents you to use it to parse arbitrary (potentially malformed)
XML. The `:xml` keyword argument causes clj-tagsoup to take into
consideration the XML header when detecting the encoding.

Installing
-------

Add then following to your deps.edn:

```clojure
clj-tagsoup/clj-tagsoup {:git/url "https://github.com/andersmurphy/clj-tagsoup"
            :git/sha "2e60e1108d20909ac00d5b8e743139f939725e70"}
```

Example
-------

```clojure
(use 'pl.danieljanus.tagsoup)
=> nil

(parse "http://example.com")
=> [:html {}
          [:head {}
                 [:title {} "Example Web Page"]]
          [:body {}
                 [:p {} "You have reached this web page by typing \"example.com\",\n\"example.net\",\n  or \"example.org\" into your web browser."]
                 [:p {} "These domain names are reserved for use in documentation and are not available \n  for registration. See "
                     [:a {:shape "rect", :href "http://www.rfc-editor.org/rfc/rfc2606.txt"} "RFC \n  2606"]
                     ", Section 3."]]]
```

Original Author
------

clj-tagsoup was written by [Daniel Janus].

 [cl-html-parse]: http://www.cliki.net/CL-HTML-Parse
 [reader]: http://richhickey.github.com/clojure-contrib/branch-1.1.x/duck-streams-api.html#clojure.contrib.duck-streams/reader
 [Daniel Janus]: http://danieljanus.pl
 [Enlive]: http://github.com/cgrand/enlive
 [TagSoup]: http://home.ccil.org/~cowan/XML/tagsoup/
 [hiccup]: http://github.com/weavejester/hiccup

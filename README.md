
# cljs-scroll-lock

### Overview

The <strong>cljs-scroll-lock</strong> is a simple ClojureScript library to controlling
the scrollability of the HTML document element.

You can add more than one prohibition with unique IDs, and if at least one prohibition
added, the scroll stays disabled.

### deps.edn

```
{:deps {bithandshake/cljs-scroll-lock {:git/url "https://github.com/bithandshake/cljs-scroll-lock"
                                       :sha     "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx"}}
```

### Current version

Check out the latest commit on the [release branch](https://github.com/bithandshake/cljs-scroll-lock/tree/release).

### Documentation

The <strong>cljs-scroll-lock</strong> functional documentation is [available here](documentation/COVER.md).

### Changelog

You can track the changes of the <strong>cljs-scroll-lock</strong> library [here](CHANGES.md).

### Index

- [How to lock the scrolling?](#how-to-lock-the-scrolling)

- [How to unlock the scrolling?](#how-to-unlock-the-scrolling)

- [How to manage more than one scroll locks at a time?](#how-to-manage-more-than-one-scroll-locks-at-a-time)

# Usage

### How to lock the scrolling?

The [`disable-scroll!`](documentation/cljs/scroll-lock/API.md/#disable-scroll) function
locks the scrolling on the page, by setting the BODY element's positioning to fixed
and setting the `overflow: hidden` property on the HTML element.

```
(disable-scroll!)
```

### How to unlock the scrolling?

The [`enable-scroll!`](documentation/cljs/scroll-lock/API.md/#enable-scroll) function
unlocks the scrolling on the page, by reversing the changes did by the `disable-scroll!`
function.

```
(enable-scroll!)
```

### How to manage more than one scroll locks at a time?

Sometimes we need to manage complex scroll locking logic, when the good old
lock / unlock / is-locked? functions no longer enough. In cases like that
the [`add-scroll-prohibition!`](documentation/cljs/scroll-lock/API.md/#add-scroll-prohibition)
and the [`remove-scroll-prohibition!`](documentation/cljs/scroll-lock/API.md/#remove-scroll-prohibition)
functions could help us. With these functions we can add or remove locks independently
from each other and the scroll stays locked until the last one removed.

For example a modal pops up and it locks the scroll by adding a prohibition with
a unique ID:

```
(add-scroll-prohibition! :my-first-lock)
```

Now the user clicks a button on the modal and one another modal pops up over the
first one and the second one locks the scroll as well as the first one:

```
(add-scroll-prohibition! :my-second-lock)
```

Now the user closes the second (upper) modal and it removes its own scroll lock:

```
(remove-scroll-prohibition! :my-second-lock)
```

That's the point on the timeline when a scroll unlocking happens (the second
modal removes its own lock) but we don't want the scroll to be reenabled again
because the first modal is still visible on the screen. And of course we don't
want the modals to know anything about each other because they are independent
UI elements. They are as simple as possible: a modal pops up and adds a lock,
then it closes and removes its previously added lock.

In cases like that, the prohibition handler does the plumbing and the only thing
we have to do is that to use unique IDs when we add or remove scroll locks.

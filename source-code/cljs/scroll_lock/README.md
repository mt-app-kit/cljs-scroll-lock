
### These settings known as they make the scroll disabling possible:

- Opera browser 76.0 (MacOS 10.15.7)
  `[:html {:style {:overflow "hidden"}}] or [:body {:style {:overflow "hidden"}}]`

- Mozilla Firefox 88.0 (MacOS 10.15.7)
  `[:html {:style {:overflow "hidden"}}] or [:body {:style {:overflow "hidden"}}]`

- Safari 13.1 (MacOS 10.15.7)
  `[:html {:style {:overflow "hidden"}}]`

- Google Chrome 90.0 (MacOS 10.15.7)
  `[:html {:style {:overflow "hidden"}}] or [:body {:style {:overflow "hidden"}}]`

- Google Chrome for mobile 86.0 (iOS 14.3, iPhone 6s)
  `[:body {:style {:position "fixed"}}]`

In mobile browser if the scroll is disabled, the user cannot refresh the page by swiping down!

To set a fixed positioning on the BODY element seemed the most reliable solution.

The `disable-dom-scroll!` function which does the work under the hood ...

... sets the HTML element 'overflow-y' property to 'hidden'

... sets the BODY element 'position' property to 'fixed'

... sets the BODY element 'width' property to '100%' (to avoid its collapsing)

... moves the BODY element (BTT) with the last scroll Y value (Y axis offset)

... marks the HTML element by a data attribute

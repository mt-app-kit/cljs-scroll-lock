
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
  @WARNING If the scroll is disabled, the user cannot refresh the page by swiping down.

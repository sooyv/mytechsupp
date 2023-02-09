function gotoProductMain() {
  const mainURL = '/';
  const url = new URL('/productMain', [mainURL]);
  return url;
}
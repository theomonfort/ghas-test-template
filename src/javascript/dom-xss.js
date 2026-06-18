// CWE-79 (DOM): Unsafe HTML construction passed to innerHTML.
// Rule: js/unsafe-jquery-plugin, js/html-injection (DOM-based XSS).
// Autofix: typically NOT provided — fix requires choosing between
// textContent, DOMPurify, or a templating library, which is a design call.

function render(container, user) {
  container.innerHTML = `
    <h2>Welcome ${user.name}</h2>
    <p>Bio: ${user.bio}</p>
    <a href="${user.homepage}">homepage</a>
  `;
}

function showSearch(container, query, results) {
  container.innerHTML =
    '<h3>Results for "' + query + '"</h3>' +
    '<ul>' + results.map(r => '<li>' + r.title + '</li>').join('') + '</ul>';
}

if (typeof window !== 'undefined') {
  window.render = render;
  window.showSearch = showSearch;
}

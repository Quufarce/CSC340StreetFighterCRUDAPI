<#macro page title active="">
    <!DOCTYPE html>
    <html lang="en">

    <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>${title} | Street Fighter 6 Characters</title>
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
      <link rel="stylesheet" href="/css/styles.css">
    </head>

    <body>
      <header class="site-header">
        <a class="brand" href="/">&#129354; Street Fighter 6 Characters</a>
        <nav>
          <a href="/" class="${(active == 'home')?then('active', '')}">Home</a>
          <a href="/about" class="${(active == 'about')?then('active', '')}">About</a>
          <a href="/characters/new" class="${(active == 'new')?then('active', '')}">Add a Character</a>
        </nav>
      </header>

      <main class="container">
          <#if message??>
            <p class="alert alert-info">${message}</p>
          </#if>
          <#nested>
      </main>

      <footer class="site-footer">Street Fighter 6 Characters demo &middot; Spring Boot MVC + FreeMarker</footer>
    </body>

    </html>
</#macro>
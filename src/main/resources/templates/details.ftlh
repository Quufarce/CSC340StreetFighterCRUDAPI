<#import "layout.ftlh" as l>
<@l.page title="${character.name}" active="home">
    <p><a href="/">Back to all characters</a></p>

    <div class="details">
        <img class="hero"
            src="/thumbnails/${(character.thumbnailUrl)!'character-placeholder.svg'}"
            alt="${character.name}" width="400" height="400">
        <div>
            <h1>${character.name}</h1>
            <dl>
                <dt>Description</dt>
                <dd>${character.description!"No description yet."}</dd>
                <dt>Type of fighter</dt>
                <dd>${character.typeOfFighter!"Unknown"}</dd>
                <dt>First appearance</dt>
                <dd>${character.firstAppearance!"Unknown"}</dd>
                <dt>Difficulty to use</dt>
                <dd>${(character.difficulty?c)!"N/A"}</dd>
            </dl>

            <div class="actions">
                <a class="btn" href="/characters/${character.characterId?c}/edit">Edit</a>
                <form method="post" action="/characters/${character.characterId?c}/delete"
                    onsubmit="return confirm('Remove this character?');">
                <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </div>
        </div>
    </div>
</@l.page>
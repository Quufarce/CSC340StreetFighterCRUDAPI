<#import "layout.ftlh" as l>
    <@l.page title="SF6 Characters" active="home">

    <h1>Street Fighter 6 Database</h1>

    <form method="get" action="/characters" class="search">
        <input type="text" name="name" value="${name!''}" placeholder="Search by name">
        <select name="difficulty">
            <option value="">Any difficulty</option>
            <#list 1..5 as d>
                <option value="${d?c}" <#if selectedDifficulty?? && selectedDifficulty == d>selected</#if>>${d}</option>
            </#list>
        </select>
        <button type="submit">Search</button>
        <#if name?has_content || selectedDifficulty??>
            <a href="/characters" class="clear">Clear</a>
        </#if>
    </form>

    <p class="count">${characters?size} character(s)</p>

    <div class="grid">
        <#list characters as character>
            <article class="card">
                <a href="/characters/${character.characterId?c}">
                    <img src="/thumbnails/${(character.thumbnailUrl)!'character-placeholder.svg'}" alt="${character.name}" width="200" height="200">
                </a>
                <div class="card-body">
                    <h3><a href="/characters/${character.characterId?c}">${character.name}</a></h3>
                    <p class="meta">Description: ${character.description?cap_first}!""</p>
                    <p class="meta">Type of Fighter: ${character.typeOfFighter!"Unknown"}</p>
                    <p class="meta">Difficulty to use: ${character.difficulty}</p>
                </div>
            </article>
        <#else>
            <p class="empty">No characters found.</p>
        </#list>
    </div>
</@l.page>
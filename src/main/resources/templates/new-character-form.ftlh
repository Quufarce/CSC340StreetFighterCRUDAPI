<#import "layout.ftlh" as l>
<@l.page title=formTitle active=(character.characterId??)?then('', 'new')>

    <h1>${formTitle}</h1>

    <form method="post" action="${formAction}" enctype="multipart/form-data" class="form">

        <div class="form-group">
            <label for="name">Name</label>
            <input id="name" name="name" type="text" value="${(character.name)!''}" required>
        </div>

        <div class="form-group">
            <label for="description">Description</label>
            <textarea id="description" name="description">${(character.description)!''}</textarea>
        </div>

        <div class="form-group">
            <label for="firstAppearance">First appearance</label>
            <input id="firstAppearance" name="firstAppearance" type="text"
                value="${(character.firstAppearance)!''}">
        </div>

        <div class="form-group">
            <label for="typeOfFighter">Type of fighter (optional)</label>
            <input id="typeOfFighter" name="typeOfFighter" type="text"
                value="${(character.typeOfFighter)!''}">
        </div>

        <div class="form-group">
            <label for="difficulty">Difficulty to use (1-5)</label>
            <select id="difficulty" name="difficulty" required>
                <option value="">Choose...</option>
                <#list 1..5 as d>
                    <option value="${d?c}"
                        <#if character.difficulty?? && character.difficulty == d>selected</#if>>${d}</option>
                </#list>
            </select>
        </div>

        <div class="form-group">
            <label for="imageFile">Picture (JPEG, PNG, or WebP, up to 1 MB)</label>
            <#if character.thumbnailUrl??>
                <img class="thumb" src="/thumbnails/${character.thumbnailUrl}"
                    alt="Current picture" width="120">
            </#if>
            <input id="imageFile" name="imageFile" type="file"
                accept="image/jpeg,image/png,image/webp">
        </div>

        <div class="actions">
            <button type="submit" class="btn">Save</button>
            <#if character.characterId??>
                <a href="/characters/${character.characterId?c}">Cancel</a>
            <#else>
                <a href="/">Cancel</a>
            </#if>
        </div>
    </form>
</@l.page>
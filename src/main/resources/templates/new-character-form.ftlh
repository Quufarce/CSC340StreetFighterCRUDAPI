<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Character Form</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
    rel="stylesheet">

    <style>
        body {
            background-color: #103B6B;
        }
        .card-body {
            background-color: #CC8D04;
            border:#CC8D04
        }
        .btn {
            background-color: #CC8D04;
            border-color: #CC8D04;
        }

    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.html">SF6 Character Database</a>
                <div class="navbar-nav">
                    <a class="nav-link active" href="about.html">About SF6</a>
            </div>
        </div>
    </nav>
    <h1 class="text-center text-white">Add a New Street Fighter 6 Character</h1>
    <div class="container mt-5">
        <form action="submit-character.php" method="post">
            <div class="mb-3">
                <label for="name" class="form-label text-white">Character Name</label>
                <input type="text" class="form-control" id="name" name="name" required>
            </div>
            <div class="mb-3">
                <label for="description" class="form-label text-white">Character Description</label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label for="image_url" class="form-label text-white">Image URL</label>
                <input type="url" class="form-control" id="image_url" name="image_url" required>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>
    <div class="mb-3"></div>
    <a href="index.html" class="btn text-white btn-primary text-dark">Back to Characters</a>
</body>
</html>
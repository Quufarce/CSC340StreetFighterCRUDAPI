<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Landing Page</title>
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
            background-color: #103B6B;
            border-color: #103B6B;
        }

    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.html">SF6 Character Database</a>
                <div class="navbar-nav">
                    <a class="nav-link active" href="about.html">About SF6</a>
                    <a class="nav-link" href="new-character-form.html">Add New SF6 Character</a>
            </div>
        </div>
    </nav>
    <div class="container mt-4">
        <div class="card mb-2">
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col-md-9">
                        <input type="text" placeholder="Search by character name..."
                            class="form-control form-control-lg">
                        </div>
                        <div class="col-md-3">
                            <button class="btn btn-secondary w-100">Search</button>
                        </div>
                    </div>
                </div>
            </div>
    <div class="container mt-5">
        <h1 class="text-center text-white">Welcome to the SF6 Character Database</h1>
        <p class="text-center text-white">Explore the characters of Street Fighter 6 and learn more about their abilities and backgrounds!</p>
    </div>
    <div class="container mt-5">
        <div class="row">
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <img src="https://www.streetfighter.com/6/assets/images/character/ryu/sns.jpg" class="card-img-top" alt="Ryu">
                    <div class="card-body">
                        <h5 class="card-title">Ryu</h5>
                        <p class="card-text">A wandering martial artist seeking to perfect his skills.</p>
                        <a href="details.html" class="btn btn-primary">View Details</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <img src="https://www.streetfighter.com/6/assets/images/character/ken/sns.jpg" class="card-img-top" alt="Ken">
                    <div class="card-body">
                        <h5 class="card-title">Ken</h5>
                        <p class="card-text">Ryu's best friend and rival, known for his fiery fighting style.</p>
                        <a href="details.html" class="btn btn-primary">View Details</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <img src="https://www.streetfighter.com/6/assets/images/character/chunli/sns.jpg" class="card-img-top" alt="Chun-Li">
                    <div class="card-body">
                        <h5 class="card-title">Chun-Li</h5>
                        <p class="card-text">An Interpol officer with a strong sense of justice and powerful kicks.</p>
                        <a href="details.html" class="btn btn-primary">View Details</a>
                    </div>
                </div>
            </div>
        <div class="container mt-5">
            <div class="row">
                <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <img src="https://www.streetfighter.com/6/assets/images/character/luke/sns.jpg" class="card-img-top" alt="Ken">
                    <div class="card-body">
                        <h5 class="card-title">Luke</h5>
                        <p class="card-text">A contractor for a PMC, Luke uses his elite military background to teach mixed martial arts.</p>
                        <a href="details.html" class="btn btn-primary">View Details</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card mb-4 shadow-sm">
                    <img src="https://www.streetfighter.com/6/assets/images/character/jamie/sns.jpg   " class="card-img-top" alt="Chun-Li">
                    <div class="card-body">
                        <h5 class="card-title">Jamie</h5>
                        <p class="card-text">A skilled martial artist with a calm demeanor and exceptional combat abilities.</p>
                        <a href="details.html" class="btn btn-primary">View Details</a>
                    </div>
                </div>
            </div>
        </div>
</body>
</html>
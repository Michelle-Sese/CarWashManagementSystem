<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="" />
    <meta name="author" content="" />
    <title>Klean car wash - Sign Up</title>
    <link href="css/styles.css" rel="stylesheet" />
    <link href="https://cdn.datatables.net/1.10.20/css/dataTables.bootstrap4.min.css" rel="stylesheet" crossorigin="anonymous" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
<div id="layoutSidenav">
    <div id="layoutSidenav_content">
        <main>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-lg-8">
                        <div class="card shadow-lg border-0 rounded-lg mt-5">
                            <div class="card-header"><h3 class="text-center font-weight-light my-4">
                               Login </h3></div>
                            <div class="card-body">
                                <form id="login-form">
                                    <div class="form-group">
                                        <label class="small mb-1" for="Email">Email</label>
                                        <input class="form-control py-4" name="email" id="Email" type="Email"/>
                                    </div>
                                    <div class="form-group">
                                        <label class="small mb-1" for="Password">Password</label>
                                            <input class="form-control py-4" name="password" id="Password" type="Password"/>
                                    </div>
                                    <div class="form-group mt-4 mb-0">
                                        <button id="login-btn" class="btn btn-primary btn-block">Sign In</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </main>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<script src="js/scripts.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
<script src="assets/demo/chart-area-demo.js"></script>
<script src="assets/demo/chart-bar-demo.js"></script>
<script src="https://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js" crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/1.10.20/js/dataTables.bootstrap4.min.js" crossorigin="anonymous"></script>
<script src="assets/demo/datatables-demo.js"></script>
<script>
    $(function(){
        $('#login-btn').click(function(e){
            e.preventDefault();
            let form = $('#login-form');
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/CarWashManagementSystem/admin/login',
                data: form.serialize(),
                dataType: 'json',
                success: function(data){
                    if(data.authenticated){
                        form.trigger('reset');
                        window.location.replace('http://localhost:8080/CarWashManagementSystem/admin');
                    }else
                        console.log(data.msg);
                },
            });
        });
    });
</script>
</body>
</html>


























































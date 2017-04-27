<nav class="navbar navbar-default">
  	<div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="index.html">Compare Cabins</a>
        </div>
        <#if username?has_content>
            <ul class="nav navbar-nav">
                <li><form action="LoadHomePage" method="post">
			         <button name="LoadHomePage" class="btn">Home</button>
		            </form>
                </li>
                <li>
                    <form action="OwnersCabins" method="post">
			         <button name="OwnersCabins" class="btn">Your Listings</button>
		            </form>
                </li>
                <li>
                    <form action="AddCabin" method="post">
                        <button name="goToCabin" class="btn">Add Cabin</button>
                    </form>
                </li>
                <li>
                    <form action="ViewEditUserProfile" method="post">
                        <button name="viewUser" class="btn">Profile</button>
                    </form>
                </li>
                <li>
                    <form action="PastStaysReview" method="post">
                        <button name="pastStays" class="btn">Past Stays</button>
                    </form>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form action="ViewEditUserProfile" method="post">
                        <button name="viewUser" class="btn">Hello ${username}</button>
                    </form>
                </li>
                <li>
                    <form action="Logout" method="post">
                        <button name="Logout" class="btn">Logout</button>
                    </form>
                </li>
            </ul>
        <#else>
        <ul class="nav navbar-nav">
          <li class="active"><a href="index.html">Home</a></li>
        </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="registerForm.html"><span class="glyphicon glyphicon-user"></span> Register</a></li>
                <li><a href="loginForm.html"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
            </ul>
        </#if>
    </div>
</nav>
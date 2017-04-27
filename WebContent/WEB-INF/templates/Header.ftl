<nav class="navbar navbar-default">
<div class="container-fluid">
<div class="navbar-header">

    <form action="LoadHomePage" method="post">
    <button name="LoadHomePage" class="btn">Compare Cabins</button>
    </form>
    
</div>
    
    <#if username?has_content>
        
        <ul class="nav navbar-nav">
            
            <li>
                <form action="LoadHomePage" method="post">
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
                
            <li>
                <form action="LoadHomePage" method="post">
			     <button name="LoadHomePage" class="btn">Home</button>
		        </form>
            </li>
            
        </ul>
        
        <ul class="nav navbar-nav navbar-right">
            
            <li>
                <form action="LoadRegisterPage" method="post">
                <button name="LoadRegisterPage" class="btn">Register</button>
                </form>
            </li>

            <li>
                <form action="LoadLoginPage" method="post">
                <button name="LoadLoginPage" class="btn">Log in</button>
                </form>
            </li>
        </ul>
    </#if>
        
</div>
</nav>
This is simple application to view GitHub's users' repositories and branches.

GET localhost:8080/repos

Necessary Header: Accept: "application/json" including Accept: "*/*" containing json media type
In request body there should be json:
{
    "userName": userName
}

Sending request on this endpoint gives the response about user's repositories (not forks), their branches and last commit's shas

Asking for not existing user's data gives 404 response with json:
{
    "status": errorCode,
    "message": errorMessage
}

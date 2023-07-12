
//Javascript function used to offer several on-page reactions/encouragements to the user's graded quiz.
//It is connected to grade_quiz_page.html.

function gradeReact(){
//Grab the text from the grade area that was populated by a Thymeleaf variable
 let gradeArea = document.getElementById("gradeArea").innerHTML;

//Take the percentage sign off the string so Javascript can treat it like a number
 let grade = gradeArea.substring(0,gradeArea.length-1);

 let message = "Good job!"; //default message in case something goes wrong

//Various messages depending on the user's grade
 if(grade >= 90)
 {

    message = "Excellent, good job! A+!";

 }
 else if(grade >= 80)
 {

    message = "Awesome! You got a B!";

 }
 else if(grade >= 70)
 {

    message = "Keeping above C-level, good going!";

 }
 else if(grade >= 60)
 {

    message = "Hey, that's pretty good! Might want to study more, though.";

 }
 else if(grade > 50)
 {

    message = "Good job, at least it's more than 50!";

 }
 else if(grade <= 50)
 {

    message = "Keep trying, you'll get there!";

 }

//Drop the message in a div below the grade display area
 document.getElementById("gradeResponse").innerHTML = message;

}

//Run the function automatically as soon as the page loads
gradeReact();
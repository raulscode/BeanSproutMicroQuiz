
//Javascript used for displaying grade on page. An experiment in using Thymeleaf with javascript. Only works as on-page script.
//Can get messy because Thymeleaf basically uses comments to indicate where variables should go in JS script.
// Formerly on QuizPage.html

//Must start with this attribute in script tag (on-page) to work with ThymeLeaf:
//<script th:inline="javascript">


    //Listen for the submit event
    document.getElementById("quizForm").addEventListener("submit",getQuizResults);

    //Function that compares answers to an answer key pulled with Thymeleaf
    function getQuizResults(event) {
    event.preventDefault();
    console.log("Submitted");

    //Grab the amount of questions by looking at the size of the list of "cards" (questions) that make upt he quiz
    let cardCount = /*[[ ${cardList.size()} ]]*/ 'default value';
    console.log(cardCount);

    //Get the answer part from each "card" and store in a map. Name the key the same as the question element in the document.
    let answerKey = new Map([
        /*[# th:each="keyCard, keyCount : ${cardList}"]*/
            [
                /*[['card' + ${#strings.toString(keyCount.index + 1)}]]*/, /*[[${keyCard.getAnswer()}]]*/,
            ],
        /*[/]*/
    ]);

    //Counter for correct answers.
    let rightAnswerCount = 0;

    //Loop through questions
    for(let i = 1; i <= cardCount; i++)
    {
        //Loop through answers for that question and check if it is right. If so, increment rightAnswerCount.
        for(let j = 1; j <= 4; j++)
        {
        let answerElement = document.getElementById("q"+i+"a"+j);
            if(answerElement.checked)
            {
                if(answerKey.get("card"+i) === j)
                {
                    rightAnswerCount++;
                }
            }
        }
    }

    //Grab the div that will be used to display the grade, then change the inner HTML.
    document.getElementById("displayGrade").innerHTML = "<p>Correct answers: " + rightAnswerCount + "</p> <p>Grade: " + Math.round((rightAnswerCount/cardCount) * 100) + "%</p>";
}

//</script>
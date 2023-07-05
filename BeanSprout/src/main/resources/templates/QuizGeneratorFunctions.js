//This is for dynamically generating the appropriate number of text inputs
function makeQuestionInputList() {

    let questionCountInput = document.getElementById("questionCountInput");
    let questionCount = parseInt(questionCountInput.value);
    let inputContainer = document.getElementById("inputContainer");

    let questionInput = document.createElement("questionInput");

    //If there were prior inputs in this div, then clear them.
    inputContainer.innerHTML = '';

    for (let i = 0; i < questionCount; i++)
    {
        questionInput.type = "text";
        input.name = "q"+i;

        inputContainer.appendChild(questionInput);
    }

}

//Add event listener to the number input and display input boxes as soon as user makes choice.
document.getElementById("questionCountInput").addEventListener("questionInput", makeQuestionInputList);
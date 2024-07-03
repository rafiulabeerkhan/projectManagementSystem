const  addMemberEl = document.getElementById("addMembers");
const  submitBtn = document.getElementById("formSubmit");
const  formEl = document.querySelector("form");
const statusELList = document.querySelectorAll("[name='status']");
const startDateEl = document.querySelector('[name="startDate"]');


let memberCount= 0

//Add Member Field Before Submit Button
function addMemberField(){
    const memberFieldEl = document.createElement("div");
    memberFieldEl.classList.add("mb-3");
    memberFieldEl.innerHTML = `
<label class="form-label">Member ${memberCount}</label>
 <input type="text"  class="form-control"  name="member" placeholder="Member Name">
`;
    inputFieldEl = memberFieldEl.querySelector("input");
    formEl.insertBefore( memberFieldEl, submitBtn);

    inputFieldEl.focus();
}

//Add Field on button click
addMemberEl.addEventListener("click", () => {

    if(memberCount < 5){
        if(memberCount != 0 && inputFieldEl.value!=""){
            memberCount+=1;
            addMemberField();
        }
        else if(memberCount == 0){
            memberCount+=1;
            addMemberField();
        }
        else {
            inputFieldEl.focus();
        }

    } else {
        inputFieldEl.focus();
    }
})

function statusHandler (e) {
    if(e.value == 0 && !startDateEl.hasAttribute("disabled")){
        startDateEl.removeAttribute("required");
        startDateEl.setAttribute("disabled", "");
        startDateEl.setAttribute("value",'');
    }else if (e.value != 0 && startDateEl.hasAttribute("disabled")){
        startDateEl.removeAttribute("disabled");
        startDateEl.setAttribute("required", "");
    }
}
statusELList.forEach(el => {
    el.setAttribute("onClick", "statusHandler(this)")
})

window.onload = () => {
    memberCount= 0
}


var showbar,navbar,task,tableContent,taskId;

window.addEventListener("load",function(){
	navbar = document.getElementById("navbar");
	showbar = false;
	task = ["Swimming"];
	fetchFromApi("http://localhost:8080/api/v1/customers");
	tableContent = document.getElementById("content");
	showAll();
});


function fetchFromApi(url){
	fetch(url)
  .then(response => {
    return response.json()
  })
  .then(data => {
    // Work with JSON data here
    for(let i=0;i<data.length;i++){
    	task.push(data[i].task);
    	showAll();
    }
  })
  .catch(err => {
    console.log("some error")
  })
}



function showResult(searchContent){
	let content="<tr><th>#</th><th>TASK</th><th>EDIT</th><th>DELETE</th></tr>";
	for(let i=0;i<task.length;i++){
		if(task[i].toLowerCase().includes(searchContent.toLowerCase())){
			content+=(`<tr id="${i}">
			<td>${i+1}</td>
			<td>${task[i]}</td>
			<td><button id = "edit" onclick="editTask(${i})">
			<i class="fa fa-pencil"></i></button></td>
			<td><button id="delete" onclick="deleteTask(${i})">
			<i class="fa fa-trash"></i></button></td>
			</tr>`);
			}
		}
		tableContent.innerHTML = content;
}

function showAll(){	
	let content="<tr><th>#</th><th>TASK</th><th>EDIT</th><th>DELETE</th></tr>";
	for(let i=0;i<task.length;i++){
		content+=(`<tr id="${i}">
			<td>${i+1}</td>
			<td>${task[i]}</td>
			<td><button id = "edit" onclick="editTask(${i})">
			<i class="fa fa-pencil"></i></button></td>
			<td><button id="delete" onclick="deleteTask(${i})">
			<i class="fa fa-trash"></i></button></td>
			</tr>`);
	}
	tableContent.innerHTML = content;
}

function deleteToApi(taskToDelete){
	const url = "http://localhost:8080/api/v1/customers/deletes";
	fetch(url,{
                method: 'DELETE',
                headers : { "content-type" : "application/json;" },
                body:JSON.stringify({task:taskToDelete})
            }).then((res) => res.json())
			  .then((data)=>console.log(data))
            .catch((err)=>console.log(err))
}

function deleteTask(indexToDelete){
	deleteToApi(task[indexToDelete]);
	task.splice(indexToDelete,1);
	showAll();
}

function editTask(indexToEdit){
 var taskToEdit = document.getElementById(indexToEdit);
 taskToEdit.childNodes[3].innerHTML = `<input type = 'text' id ="editBox" value=${task[indexToEdit]}>`;
 taskToEdit.childNodes[5].innerHTML=`<button onclick="replace(${indexToEdit})" id="edit">
 <i class="fa fa-check"></i></button>`;
}

function putToApi(originalTask , replacedTask){
	const url = "http://localhost:8080/api/v1/customers/puts";
	fetch(url,{
                method: 'PUT',
                headers : { "content-type" : "application/json;" },
                body:JSON.stringify([{task:originalTask},{task:replacedTask}])
            }).then((res) => res.json())
			  .then((data)=>console.log(data))
            .catch((err)=>console.log(err))
}

function replace(indexToReplace){
	var newValue = document.getElementById("editBox").value;
	if(newValue!=""){
		putToApi(task[indexToReplace],newValue);
		task[indexToReplace] =  newValue;
	}
	showAll();
}

function act(){
	var searchContent = document.getElementById("searchBox").value;
	if(searchContent!=""){
		showResult(searchContent);
	}
}

function postToApi(taskToAdd){
	const url = "http://localhost:8080/api/v1/customers/posts";
	fetch(url,{
                method: 'POST',
                headers : { "content-type" : "application/json;" },
                body:JSON.stringify({task:taskToAdd})
            }).then((res) => res.json())
            .then((data) =>  console.log(data))
            .catch((err)=>console.log(err))
}

function addTask(){
	var taskToAdd = document.getElementById("searchBox").value;
	if(taskToAdd!=""){
		postToApi(taskToAdd);
		task.push(taskToAdd);
	}
	document.getElementById("searchBox").value="";
	showAll();
}


function showBar(){
	if(showbar==false){
		navbar.style.left="0vw";
		showbar=true;
	}
	else{
		navbar.style.left="-22vw";
		showbar=false;
	}
}


function containFun(){
	navbar.style.left="-22vw";
}
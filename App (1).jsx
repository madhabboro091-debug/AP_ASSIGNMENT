import { useState, useEffect } from "react";

function App() {

const [students,setStudents] = useState(new Map([

["s1",{id:"s1",name:"Aarav Sharma",cgpa:8.2,enrolledCourses:new Set(["DAA","OS","DBMS","AI","ML"])}],
["s2",{id:"s2",name:"Riya Gupta",cgpa:7.5,enrolledCourses:new Set(["DAA","ECONOMICS","OS","DBMS","ML"])}],
["s3",{id:"s3",name:"Rahul Verma",cgpa:8.8,enrolledCourses:new Set(["AI","ML","DBMS","OS","ROBOTICS"])}],
["s4",{id:"s4",name:"Ananya Das",cgpa:7.9,enrolledCourses:new Set(["DAA","AI","ML","ECONOMICS","OS"])}],
["s5",{id:"s5",name:"Arjun Patel",cgpa:8.5,enrolledCourses:new Set(["ROBOTICS","AI","ML","DBMS","OS"])}],

["s6",{id:"s6",name:"Priya Singh",cgpa:7.2,enrolledCourses:new Set(["DAA","ECONOMICS","OS","AI","ML"])}],
["s7",{id:"s7",name:"Karan Mehta",cgpa:9.1,enrolledCourses:new Set(["AI","ML","DBMS","ROBOTICS","OS"])}],
["s8",{id:"s8",name:"Neha Kapoor",cgpa:8.0,enrolledCourses:new Set(["DAA","OS","DBMS","ML","AI"])}],
["s9",{id:"s9",name:"Rohan Chatterjee",cgpa:7.7,enrolledCourses:new Set(["ECONOMICS","DAA","OS","AI","ML"])}],
["s10",{id:"s10",name:"Sneha Nair",cgpa:8.6,enrolledCourses:new Set(["ROBOTICS","AI","ML","OS","DBMS"])}],

["s11",{id:"s11",name:"Aditya Kulkarni",cgpa:7.4,enrolledCourses:new Set(["DAA","OS","DBMS","AI","ML"])}],
["s12",{id:"s12",name:"Pooja Reddy",cgpa:8.3,enrolledCourses:new Set(["AI","ML","ROBOTICS","OS","DBMS"])}],
["s13",{id:"s13",name:"Vikram Iyer",cgpa:8.9,enrolledCourses:new Set(["AI","ML","DAA","OS","DBMS"])}],
["s14",{id:"s14",name:"Megha Joshi",cgpa:7.6,enrolledCourses:new Set(["ECONOMICS","DAA","OS","AI","ML"])}],
["s15",{id:"s15",name:"Siddharth Roy",cgpa:8.1,enrolledCourses:new Set(["ROBOTICS","AI","ML","OS","DBMS"])}],

["s16",{id:"s16",name:"Tanvi Deshmukh",cgpa:7.8,enrolledCourses:new Set(["DAA","OS","AI","ML","DBMS"])}],
["s17",{id:"s17",name:"Harsh Vardhan",cgpa:8.4,enrolledCourses:new Set(["AI","ML","ROBOTICS","OS","DBMS"])}],
["s18",{id:"s18",name:"Ishita Banerjee",cgpa:7.9,enrolledCourses:new Set(["DAA","AI","ML","OS","DBMS"])}],
["s19",{id:"s19",name:"Dev Malhotra",cgpa:8.7,enrolledCourses:new Set(["AI","ML","ROBOTICS","OS","DBMS"])}],
["s20",{id:"s20",name:"Nisha Agarwal",cgpa:7.3,enrolledCourses:new Set(["DAA","ECONOMICS","OS","AI","ML"])}]

]));

const [courses,setCourses] = useState([
"DAA","ECONOMICS","CAO LAB","ROBOTICS","OS","DBMS","AI","ML"
]);

const [filterCourses,setFilterCourses] = useState(new Set());
const [filteredStudents,setFilteredStudents] = useState([]);
const [generateCount,setGenerateCount] = useState(1000);
const [showAll,setShowAll] = useState(false);

const [newName,setNewName] = useState("");
const [newCgpa,setNewCgpa] = useState("");
const [newCourses,setNewCourses] = useState(new Set());
const [newCourseName,setNewCourseName] = useState("");

const names = Array.from({length:1000},(_,i)=>`Student ${i+1}`);

const random = arr => arr[Math.floor(Math.random()*arr.length)];

const randomCGPA = () => (Math.random()*3+6).toFixed(2);

const randomCourses = () => {

const count = Math.floor(Math.random()*3)+5;

const set = new Set();

while(set.size < count && set.size < courses.length){

set.add(random(courses));

}

return set;

};

const generateStudents = () => {

const map = new Map(students);

for(let i=0;i<generateCount;i++){

const id = Math.random().toString(36).slice(2,8);

map.set(id,{
id,
name:random(names),
cgpa:parseFloat(randomCGPA()),
enrolledCourses:randomCourses()
});

}

setStudents(map);

};

const addStudent = () => {

const id = Math.random().toString(36).slice(2,8);

setStudents(prev=>new Map(prev).set(id,{
id,
name:random(names),
cgpa:parseFloat(randomCGPA()),
enrolledCourses:randomCourses()
}));

};

const removeStudent = id => {

const map = new Map(students);

map.delete(id);

setStudents(map);

};

const toggle = (set,value) => {

const s = new Set(set);

s.has(value) ? s.delete(value) : s.add(value);

return s;

};

const addManualStudent = () => {

if(!newName || !newCgpa || newCourses.size < 5){

alert("Student must have at least 5 courses");

return;

}

const id = Math.random().toString(36).slice(2,8);

setStudents(prev=>new Map(prev).set(id,{
id,
name:newName,
cgpa:parseFloat(newCgpa),
enrolledCourses:new Set(newCourses)
}));

setNewName("");

setNewCgpa("");

setNewCourses(new Set());

};

const addCourse = () => {

if(!newCourseName) return;

if(courses.includes(newCourseName)){

alert("Course already exists");

return;

}

setCourses([...courses,newCourseName]);

setNewCourseName("");

};

useEffect(()=>{

const result = [];

for(const student of students.values()){

if(filterCourses.size===0){

result.push(student);

continue;

}

for(const c of student.enrolledCourses){

if(filterCourses.has(c)){

result.push(student);

break;

}

}

}

result.sort((a,b)=>b.cgpa-a.cgpa);

setFilteredStudents(result);

},[students,filterCourses]);

const visibleStudents = showAll ? filteredStudents : filteredStudents.slice(0,200);

return (

<div style={{padding:30,fontFamily:"Arial"}}>

<h1>🎓 Course Enrollment Dashboard</h1>

<h3>Add New Course</h3>

<input
placeholder="Course Name"
value={newCourseName}
onChange={e=>setNewCourseName(e.target.value)}
/>

<button onClick={addCourse}>Add Course</button>

<h3>Generate Random Students</h3>

<input
type="number"
value={generateCount}
onChange={e=>setGenerateCount(Number(e.target.value))}
/>

<button onClick={generateStudents}>Generate Students</button>

<button onClick={addStudent}>Add Random Student</button>

<h3>Total Students: {students.size}</h3>

<h3>Manually Add Student</h3>

<input
placeholder="Name"
value={newName}
onChange={e=>setNewName(e.target.value)}
/>

<input
type="number"
step="0.01"
placeholder="CGPA"
value={newCgpa}
onChange={e=>setNewCgpa(e.target.value)}
/>

<div>

{courses.map(course=>(

<label key={course} style={{marginRight:10}}>

<input
type="checkbox"
checked={newCourses.has(course)}
onChange={()=>setNewCourses(toggle(newCourses,course))}
/>

{course}

</label>

))}

</div>

<button onClick={addManualStudent}>Add Student</button>

<h3>Filter by Course</h3>

{courses.map(course=>(

<label key={course} style={{marginRight:15}}>

<input
type="checkbox"
checked={filterCourses.has(course)}
onChange={()=>setFilterCourses(toggle(filterCourses,course))}
/>

{course}

</label>

))}

<h3>Display Mode</h3>

<button onClick={()=>setShowAll(false)}>Show Top 200</button>

<button onClick={()=>setShowAll(true)}>Show All Students</button>

<h3>Students ({visibleStudents.length} shown)</h3>

<ul>

{visibleStudents.map(student=>(

<li key={student.id} style={{marginBottom:10}}>

<strong>{student.name}</strong>

{" | "}CGPA: {student.cgpa}

{" | "}Courses: {[...student.enrolledCourses].join(", ")}

<button
style={{marginLeft:10}}
onClick={()=>removeStudent(student.id)}
>
Remove
</button>

</li>

))}

</ul>

</div>

);

}

export default App;
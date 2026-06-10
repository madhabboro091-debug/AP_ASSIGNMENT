import React, { useState } from "react";

function Assignment4() {
  const [todos, setTodos] = useState([]);
  const [input, setInput] = useState("");
  const [priority, setPriority] = useState("Medium");
  const [section, setSection] = useState("General");
  const [sections, setSections] = useState(["General"]);

  const addTodo = () => {
    const trimmed = input.trim();
    if (!trimmed) return;

    setTodos([
      ...todos,
      {
        text: trimmed,
        completed: false,
        editing: false,
        priority,
        section,
      },
    ]);

    setInput("");
  };

  const toggleComplete = (index) => {
    const newTodos = [...todos];
    newTodos[index].completed = !newTodos[index].completed;
    setTodos(newTodos);
  };

  const deleteTodo = (index) => {
    setTodos(todos.filter((_, i) => i !== index));
  };

  const startEditing = (index) => {
    const newTodos = [...todos];
    newTodos[index].editing = true;
    setTodos(newTodos);
  };

  const saveEdit = (index, newText) => {
    const newTodos = [...todos];
    newTodos[index].text = newText;
    newTodos[index].editing = false;
    setTodos(newTodos);
  };

  const addSection = () => {
    const name = prompt("Enter new section name:");
    if (name && !sections.includes(name)) {
      setSections([...sections, name]);
    }
  };

  const deleteAll = () => {
    if (window.confirm("Delete all todos?")) {
      setTodos([]);
    }
  };

  const groupedTodos = sections.map((sec) => ({
    name: sec,
    items: todos.filter((todo) => todo.section === sec),
  }));

  return (
    <div style={styles.container}>
      <h1>TODO LIST</h1>

      <div style={styles.inputContainer}>
        <input
          type="text"
          placeholder="Type your todo..."
          value={input}
          onChange={(e) => setInput(e.target.value)}
          style={styles.input}
        />

        <select
          value={priority}
          onChange={(e) => setPriority(e.target.value)}
        >
          <option>High</option>
          <option>Medium</option>
          <option>Low</option>
        </select>

        <select
          value={section}
          onChange={(e) => setSection(e.target.value)}
        >
          {sections.map((sec, i) => (
            <option key={i}>{sec}</option>
          ))}
        </select>

        <button onClick={addTodo}>Add</button>
      </div>

      <button onClick={addSection}>+ Add Section</button>

      {groupedTodos.map((group, i) => (
        <div key={i}>
          <h2>{group.name}</h2>

          <ul style={{ listStyle: "none", padding: 0 }}>
            {group.items.map((todo) => {
              const index = todos.findIndex((t) => t === todo);

              return (
                <li key={index} style={styles.todoItem}>
                  <input
                    type="checkbox"
                    checked={todo.completed}
                    onChange={() => toggleComplete(index)}
                  />

                  {todo.editing ? (
                    <input
                      defaultValue={todo.text}
                      onBlur={(e) =>
                        saveEdit(index, e.target.value)
                      }
                      autoFocus
                    />
                  ) : (
                    <span
                      style={{
                        textDecoration: todo.completed
                          ? "line-through"
                          : "none",
                      }}
                    >
                      {todo.text}
                    </span>
                  )}

                  <button onClick={() => startEditing(index)}>
                    Edit
                  </button>

                  <button onClick={() => deleteTodo(index)}>
                    Delete
                  </button>
                </li>
              );
            })}
          </ul>
        </div>
      ))}

      {todos.length > 0 && (
        <button onClick={deleteAll}>
          Delete All
        </button>
      )}
    </div>
  );
}

const styles = {
  container: {
    maxWidth: "700px",
    margin: "40px auto",
    padding: "20px",
    background: "#fff",
  },
  inputContainer: {
    display: "flex",
    gap: "10px",
    marginBottom: "15px",
  },
  input: {
    flex: 1,
    padding: "10px",
  },
  todoItem: {
    display: "flex",
    gap: "10px",
    alignItems: "center",
    marginBottom: "10px",
  },
};

export default Assignment4;
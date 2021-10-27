import {useState} from "react";
import {Redirect} from "react-router";

export default function PostForm({user, post = null, closeForm}) {
  const [title, setTitle] = useState(post === null ? "" : post.title);
  const [content, setContent] = useState(post === null ? "" : post.content);
  const [redirect, setRedirect] = useState(false);
  const [errors, setErrors] = useState([]);

  const handleTitleChange = e => setTitle(e.target.value)
  const handleContentChange = e => setContent(e.target.value)

  const handleSubmit = e => {
    e.preventDefault();

    fetch("http://localhost:8080/posts", {
      method: post === null ? "POST" : "PUT",
      headers: {
        "content-type": "application/json"
      },
      body: JSON.stringify({
        id: post === null ? null : post.id,
        title: title,
        content: content,
        userId: user.id
      })
    }).then(res => res.json())
      .then(res => {
        if (res.message === "Invalid payload") {
          setErrors(res.details);
        } else {
          post === null ? setRedirect(!redirect) : window.location.reload()
        }
      })
      .catch(e => console.error(e));
  }

  return (
    <form onSubmit={handleSubmit}>
      {redirect && <Redirect to="/" />}

      <div className={"inputField"}>
        <label>Enter title:</label>
        <input onChange={handleTitleChange} value={title} />
      </div>

      <div className={"inputField"}>
        <label>Enter post content:</label>
        <textarea onChange={handleContentChange} value={content} />
      </div>

      <input type="submit" value="Submit" />
      {
        post !== null && <button onClick={closeForm}>Cancel</button>
      }

      <div>
        {errors.map((error, index) => <p key={index}>{error}</p>)}
      </div>
    </form>
  )
}
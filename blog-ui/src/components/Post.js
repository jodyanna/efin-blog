import {useState, useEffect} from "react";
import {useParams, Redirect} from "react-router";
import PostForm from "./PostForm";

export default function Post({user}) {
  const [post, setPost] = useState(null);
  const [isFetching, setIsFetching] = useState(true);
  const [isEditForm, setIsEditForm] = useState(false);
  const [redirect, setRedirect] = useState(false);
  const {id} = useParams();

  useEffect(() => {
    const abortController = new AbortController();

    const fetchPost = () =>{
      fetch("http://localhost:8080/posts/" + id, {
        method: "GET",
        headers: {
          "content-type": "application/json"
        },
        signal: abortController.signal
      }).then(res => res.json())
        .then(res => {
          setPost(res);
          setIsFetching(false);
        })
        .catch(e => {
          if (!abortController.signal.aborted) {
            console.error(e);
          }
        });
    }

    fetchPost();

    return () => {
      abortController.abort();
    }
  }, [id, post]);

  const handleEditClick = () => setIsEditForm(!isEditForm)

  const handleDeleteClick = () => {
    fetch("http://localhost:8080/posts/" + post.id, {
      method: "DELETE"
    }).then(() => setRedirect(true))
      .catch(e => console.error(e))
  }

  return (
    <article>
      {redirect && <Redirect to="/" />}
      {
        isEditForm ?
          <PostForm user={user} post={post} closeForm={handleEditClick} />
          :
          !isFetching &&
          <div>
            <h3>{post.title}</h3>
            <p>{post.content}</p>
            <button onClick={handleEditClick}>Edit</button>
            <button onClick={handleDeleteClick}>Delete</button>
          </div>
      }
    </article>
  )
}
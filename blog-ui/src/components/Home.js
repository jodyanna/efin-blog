import {useEffect, useState} from "react";
import {Link} from "react-router-dom";

export default function Home() {
  const [posts, setPosts] = useState([]);

  useEffect(() => {
    fetch("http://localhost:8080/posts", {
      method: "GET",
      headers: {
        "content-type": "application/json"
      }
    }).then(res => res.json())
      .then(res => setPosts(res))
      .catch(e => console.error(e));
  }, [])

  return (
    <div>
      <h2>Blogs</h2>
      <ul>
        {posts.map(post => <li key={post.id}><Link to={"/blogs/" + post.id}>{post.title}</Link></li>)}
      </ul>
    </div>
  )
}
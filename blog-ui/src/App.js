import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";
import Nav from "./components/Nav";
import PostForm from "./components/PostForm";
import Home from "./components/Home";
import Post from "./components/Post";

const user = {
  id: 1,
  email: "test@email.com",
  password: "password"
}

function App() {
  return (
    <Router>
      <div className={"app"}>
        <Nav />
        <Switch>
          <Route path="/create">
            <PostForm user={user} />
          </Route>

          <Route path="/blogs/:id">
            <Post user={user} />
          </Route>

          <Route path="/">
            <Home user={user} />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;

export async function fetchGet(url) {
  const res = await fetch(url, {
    method: "GET",
    headers: {
      "Content-Type": "application/json",
    },
  });

  console.log("=====> fetch get");
  console.log("url: ", url);
  console.log("res: ", res);
  return res;
}

export async function fetchPost(url, obj) {
  const res = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(obj),
  });

  console.log("=====> fetch post");
  console.log("url: ", url);
  console.log("res: ", res);
  return res;
}

export async function fetchPut(url, obj) {
  const res = await fetch(url, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(obj),
  });

  console.log("=====> fetch put");
  console.log("url: ", url);
  console.log("res: ", res);
  return res;
}

export async function fetchDelete(url, obj) {
  const res = await fetch(url, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
      //"X-XSRF-TOKEN": token,
    },
    body: JSON.stringify(obj),
  });

  console.log("=====> fetch delete");
  console.log("url: ", url);
  console.log("res: ", res);
  return res;
}

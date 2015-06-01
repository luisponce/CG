using UnityEngine;
using System.Collections;

public class DrawFigure : MonoBehaviour {

	Vector3[] vertex = new Vector3[] {new Vector3(0,0,0), new Vector3(5,0,0), new Vector3(0,0,5), new Vector3(5,5,5)};

	public GameObject vertexSphere;

	// Use this for initialization
	void Start () {
		for(int i = 0; i<vertex.Length; i++){
			GameObject obj = (GameObject) GameObject.Instantiate(vertexSphere, vertex[i], Quaternion.identity);
			obj.transform.parent = this.transform;
		}
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}

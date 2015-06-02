using UnityEngine;
using System.Collections;

public class DrawFigure : MonoBehaviour {

	public Vector3[] vertex;

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

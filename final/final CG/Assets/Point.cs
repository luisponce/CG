using UnityEngine;
using System.Collections;

public class Point : MonoBehaviour {

	public Material red;
	public Material green;

	public Vector3 initialPosition;

	public Vector3[] vertices ;

	public int[,] sides;

	float speed = 1f;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
		move();

		paint();
	}

	private void paint(){

		Material m;
		if(isInside()){
			//red
			m=red;
		} else {
			//green
			m=green;
		}

		GetComponent<MeshRenderer>().material = m;
	}

	private bool isInside(){
		Vector3 pos = transform.position;

		bool inside = true;
		for (int i = 0; i< 4 && inside; i++){//cada cara

			Vector3 p0 = vertices[sides[i,0]];
			Vector3 p1 = vertices[sides[i,1]];
			Vector3 p2 = vertices[sides[i,2]];

			Vector3 v1 = p1 - p0;
			Vector3 v2 = p2 - p0;

			Vector3 n = Vector3.Cross(v1,v2);


			float d = n.x * p0.x + n.y * p0.y + n.z * p0.z;
			float eq = n.x * pos.x + n.y * pos.y + n.z * pos.z - d;


			if(eq > 0) inside = false;
		}

		return inside;
	}

	private void move(){
		Vector3 dir = new Vector3(0,0,0);
		
		if(Input.GetKey(KeyCode.A)){
			//-x
			dir.x = -1;
		}
		if(Input.GetKey(KeyCode.D)){
			//+x
			dir.x = 1;
		}
		
		if(Input.GetKey(KeyCode.W)){
			//+y
			dir.y = 1;
		}
		if(Input.GetKey(KeyCode.S)){
			//-y
			dir.y = -1;
		}
		
		if(Input.GetKey(KeyCode.O)){
			//-z
			dir.z = 1;
		}
		if(Input.GetKey(KeyCode.L)){
			//+z
			dir.z = -1;
		}
		
		transform.Translate(dir * speed * Time.deltaTime);
	}
}

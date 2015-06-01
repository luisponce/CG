using UnityEngine;
using System.Collections;

public class Point : MonoBehaviour {

	Vector3 initialPosition = new Vector3(2,2,2);

	float speed = 1f;

	// Use this for initialization
	void Start () {
	
	}
	
	// Update is called once per frame
	void Update () {
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

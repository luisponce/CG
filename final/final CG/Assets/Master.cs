﻿using UnityEngine;
using System.Collections;

public class Master : MonoBehaviour {
	TextAsset textFile; 
	//TextAsset textFile = Resources.Load("Input.txt") as TextAsset;

	public GameObject p;//the point to move
		
	void Awake(){
		textFile = Resources.Load("Input") as TextAsset;

		string text = textFile.text;  //this is the content as string

		string[]lines = text.Split('\n');
		//string[] entries;
		Vector3[] vertices = new Vector3[4];
		int[,] sides = new int[4, 3];
		Vector3 point = new Vector3(0,0,0);
		for (int i = 0; i<lines.Length; ++i) {
			if(i<=3) {
				string[] entries = lines[i].Split (' ');
				string pointSx = entries [0];
				float pointx = float.Parse(pointSx,System.Globalization.CultureInfo.InvariantCulture);
				string pointSy = entries [1];
				float pointy = float.Parse(pointSy,System.Globalization.CultureInfo.InvariantCulture);
				string pointSz = entries [2];
				float pointz = float.Parse(pointSz,System.Globalization.CultureInfo.InvariantCulture);
				vertices[i]=new Vector3(pointx,pointy,pointz);
			}
			else if (i<=7){
				string[] entries = lines[i].Split (' ');
				for(int j=0; j<entries.Length; ++j){
					string element = entries[j];
					int e = int.Parse(element);
					sides[i-4,j]=e;
				}
			}
			else {
				string[] entries = lines[i].Split (' ');
				string pointSx = entries [0];
				float pointx = float.Parse(pointSx,System.Globalization.CultureInfo.InvariantCulture);
				string pointSy = entries [1];
				float pointy = float.Parse(pointSy,System.Globalization.CultureInfo.InvariantCulture);
				string pointSz = entries [2];
				float pointz = float.Parse(pointSz,System.Globalization.CultureInfo.InvariantCulture);
				point=new Vector3(pointx,pointy,pointz);
			}



		}
		//byte[] byteText = textFile.bytes;  //this is the content as byte array
		//Debug.Log (text);
		/*string point1x = entries [0];
		float pointx0 = float.Parse(point1x);
		Debug.Log (pointx0);
		string point1y = entries [1];
		Debug.Log (point1y);
		string point1z = entries [2];
		Debug.Log (point1z);*/


		//debug
		for(int i=0;i<4;i++){
			string str = "";
			for(int j = 0; j < 3; j++){
				str += sides[i,j];
			}
			
			Debug.Log(str);
		}

		//send info to other entities
		GetComponent<DrawFigure>().vertex = vertices;
		p.GetComponent<Point>().initialPosition = point;
		p.GetComponent<Point>().vertices = vertices;
		p.GetComponent<Point>().sides = sides;
	}

	void Start(){
	}
	
	// Update is called once per frame
	void Update () {
	}
}

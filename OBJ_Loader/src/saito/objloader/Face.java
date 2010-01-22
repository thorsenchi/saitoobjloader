package saito.objloader;

/*
 * Alias .obj loader for processing
 * programmed by Tatsuya SAITO / UCLA Design | Media Arts 
 * Created on 2005/04/17
 *
 * 
 *  
 */

import java.util.ArrayList;

import processing.core.PConstants;
import processing.core.PVector;

/**
 * @author tatsuyas
 * @author mattDitton
 * @author Ekene Ijeoma
 * 
 *         Each model element contains the indexes to the vertices, normals and
 *         UV's needed to make a face
 */

public class Face implements PConstants {

	public int indexType = POLYGON;

	public ArrayList<Integer> vertexIndices;
	public ArrayList<Integer> uvIndices;
	public ArrayList<Integer> normalIndices;

	public ArrayList<PVector> vertices;
	public ArrayList<PVector> normals;
	public ArrayList<PVector> uvs;
	
	/**
	 * Constructor for the Element. A model element is all the indices needed to
	 * draw a face.
	 */
	public Face() {
		vertexIndices = new ArrayList<Integer>();
		uvIndices = new ArrayList<Integer>();
		normalIndices = new ArrayList<Integer>();

		vertices = new ArrayList<PVector>();
		normals = new ArrayList<PVector>();
		uvs = new ArrayList<PVector>();
	}

	public int getIndexCount() {
		return vertexIndices.size();
	}
	
	public int getVertexCount() {
		return vertices.size();
	}
	
	public int getNormalCount() {
		return normals.size();
	}
	
	public int getUVCount() {
		return uvs.size();
	}

	public int[] getVertexIndices() {
		int[] v = new int[getIndexCount()];

		for (int i = 0; i < v.length; i++)
			v[i] = getVertexIndex(i);

		return v;
	}

	public int[] getNormalIndices() {
		int[] v = new int[getIndexCount()];

		for (int i = 0; i < v.length; i++)
			v[i] = getNormalIndex(i);

		return v;
	}

	public int[] getTextureIndices() {
		int[] v = new int[getIndexCount()];

		for (int i = 0; i < v.length; i++)
			v[i] = getTextureIndex(i);

		return v;
	}

	public PVector[] getVertices() {
		return vertices.toArray(new PVector[vertices.size()]);
	}

	public PVector[] getNormals() {
		return normals.toArray(new PVector[normals.size()]);
	}

	public PVector[] getUvs() {
		return uvs.toArray(new PVector[uvs.size()]);
	}

	public PVector getCenter() {
		PVector c = new PVector();

		for (int i = 0; i < vertices.size(); i++)
			c.add(vertices.get(i));

		c.div(vertices.size());
		
		return c;
	}
	
	public PVector getNormal() {
		// center vertex
		PVector c = getCenter();

		// center - first vertex
		PVector aToB = PVector.sub(c, vertices.get(0));
		// center - last vertex
		PVector cToB = PVector.sub(c, vertices.get(vertices.size() - 1));
		PVector n = cToB.cross(aToB);

		return n.normalize(new PVector(1, 1, 1));
	}

	// Arrays start at 0 (hence the -1) But OBJ files start the
	// indices at 1.
	public int getVertexIndex(int i) {
		return vertexIndices.get(i) - 1;
	}

	public int getTextureIndex(int i) {
		return uvIndices.get(i) - 1;
	}

	public int getNormalIndex(int i) {
		return normalIndices.get(i) - 1;
	}
}
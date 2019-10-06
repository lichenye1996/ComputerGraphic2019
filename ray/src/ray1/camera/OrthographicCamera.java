package ray1.camera;

import ray1.Ray;
import egl.math.Vector3d;

public class OrthographicCamera extends Camera {

    //TODO#Ray Task 1: create necessary new variables/objects here, including an orthonormal basis
    //          formed by three basis vectors and any other helper variables 
    //          if needed.
    Vector3d u;
    Vector3d v;
    Vector3d w;
    Vector3d e;
    Vector3d d;
    
    
    /**
     * Initialize the derived view variables to prepare for using the camera.
     */
    public void init() {
        // TODO#Ray Task 1:  Fill in this function.
        // 1) Set the 3 basis vectors in the orthonormal basis, 
        //    based on viewDir and viewUp
        Vector3d viewDir = new Vector3d((double)getViewDir().x, (double)getViewDir().y, (double)getViewDir().z);
        Vector3d viewPoint = new Vector3d((double)getViewPoint().x, (double)getViewPoint().y, (double)getViewPoint().z);
        Vector3d viewUp = new Vector3d((double)getViewUp().x, (double)getViewUp().y, (double)getViewUp().z);
        d = (new Vector3d(viewDir.x, viewDir.y, viewDir.z)).normalize();
        w = d.clone().negate().normalize();
        u = viewUp.clone().cross(w.clone()).normalize().clone();
        v = w.clone().cross(u).normalize().clone();
        e = viewPoint.clone();
        // 2) Set up the helper variables if needed


    }

    /**
     * Set outRay to be a ray from the camera through a point in the image.
     *
     * @param outRay The output ray (not normalized)
     * @param inU The u coord of the image point (range [0,1])
     * @param inV The v coord of the image point (range [0,1])
     */
    public void getRay(Ray outRay, float inU, float inV) {
        // TODO#Ray Task 1: Fill in this function.
        // 1) Transform inU so that it lies between [-viewWidth / 2, +viewWidth / 2] 
        //    instead of [0, 1]. Similarly, transform inV so that its range is
        //    [-vieHeight / 2, +viewHeight / 2]
        double width = getViewWidth();
        double height = getViewHeight();
        double transInU = ((double)inU/1.) * width - width / 2.;
        double transInV = ((double)inV/1.) * height - height / 2.;
        // 2) Set the origin field of outRay for an orthographic camera. 
        //    In an orthographic camera, the origin should depend on your transformed
        //    inU and inV and your basis vectors u and v.
        Vector3d origin = e.clone().add(u.clone().mul(transInU)).add(v.clone().mul(transInV)).clone();
        Vector3d rayDir = d.clone();
        // 3) Set the direction field of outRay for an orthographic camera.
        outRay.set(origin, rayDir);

        

    }

}

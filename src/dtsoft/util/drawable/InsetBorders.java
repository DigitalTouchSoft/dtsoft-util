package dtsoft.util.drawable;

import android.graphics.drawable.ShapeDrawable;

public class InsetBorders {
	private ShapeDrawable mInsetLeft;
	private ShapeDrawable mInsetRight;
	private ShapeDrawable mInsetTop;
	private ShapeDrawable mInsetBottom;
	
	public ShapeDrawable getInsetLeft() { 
		return mInsetLeft;
	}
	public void setInsetLeft(ShapeDrawable insetLeft) { 
		mInsetLeft = insetLeft;
	}

	public ShapeDrawable getInsetRight() { 
		return mInsetRight;
	}
	public void setInsetRight(ShapeDrawable insetRight) { 
		mInsetRight = insetRight;
	}
	
	public ShapeDrawable getInsetTop() { 
		return mInsetTop;
	}
	public void setInsetTop(ShapeDrawable insetTop) { 
		mInsetTop = insetTop;
	}

	public ShapeDrawable getInsetBottom() { 
		return mInsetBottom;
	}
	public void setInsetBottom(ShapeDrawable insetBottom) { 
		mInsetBottom = insetBottom;
	}
}


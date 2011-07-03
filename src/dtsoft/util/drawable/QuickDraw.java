package dtsoft.util.drawable;

import android.graphics.LinearGradient;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.util.Log;

public class QuickDraw {
	private static float[] ROUNDED_CORNERS = new float[] { 12, 12, 12, 12, 12, 12, 12, 12 };

	public QuickDraw () { 
		
	}
	
	/***
	 * Draws a basic rounded rectangle given the x / y width / height and inset values
	 * @param left
	 * 	The left position
	 * @param top
	 * 	The left position
	 * @param right
	 * 	the right position
	 * @param bottom
	 * 	the bottom position
	 * @param inset
	 * 	Whether or not to draw an inset, set to null if not needed
	 * @return
	 */
	public static ShapeDrawable drawRoundRectangle(int left, int top, int right, int bottom, RectF inset) { 
		RoundRectShape rrs = new RoundRectShape(ROUNDED_CORNERS, inset, null);
		ShapeDrawable roundedRect = new ShapeDrawable(rrs);
		roundedRect.setBounds(
				left, 
				top, 
				right, 
				bottom);
		roundedRect.getPaint().setAntiAlias(true);
		roundedRect.getPaint().setDither(true);		
		return roundedRect;
	}
	
	/**
	 * Adds color in the form of a linear from top to bottom (no angle) using the colors / shape / drawingRect supplied using TileMode.MIRROR
	 * @param color0
	 * @param color1
	 * @param shape
	 * @param drawingRect
	 */
	public static void colorShapeLinear(int color0, int color1, ShapeDrawable shape, Rect drawingRect) {
		LinearGradient background = new LinearGradient(0, 0, 0, drawingRect.height(), color0, color1, TileMode.MIRROR);
		shape.getPaint().setShader(background);
	}

	public static ShapeDrawable drawInset(
			Rect drawingRect, 
			int color0,
			int color1,
			int paddingLeft, 
			int paddingTop, 
			int paddingRight, 
			int paddingBottom, 
			float insetBufferW,
			float insetBufferH) {
		RectShape rs = new RectShape();
		ShapeDrawable inset = new ShapeDrawable(rs);
//		Log.i("QuickDraw", "Rectangle Constraints (ltrb): " + 
//					(drawingRect.left + paddingLeft) +  " " + 
//					(drawingRect.top + paddingTop) + " " + 
//					(drawingRect.right - paddingRight) + " " + 
//					(drawingRect.bottom - paddingBottom));
//		Log.i("QuickDraw", "InsetBufferH: " + insetBufferH + " InsetBufferW: " + insetBufferW);
		inset.setBounds(
				(int)(drawingRect.left + insetBufferW + paddingLeft), 
				(int)(drawingRect.top + insetBufferH + paddingTop), 
				(int)(drawingRect.right - insetBufferW - paddingRight), 
				(int)(drawingRect.bottom - insetBufferH - paddingBottom));
		inset.getPaint().setAntiAlias(true);
		inset.getPaint().setDither(true);
		inset.getPaint().setShader(new RadialGradient(
				inset.getBounds().width(), 
				inset.getBounds().height(), 
				(float) Math.sqrt(Math.pow(inset.getBounds().width(), 2) + Math.pow(inset.getBounds().height(), 2)), 
				color0, 
				color1, 
				TileMode.MIRROR));

		return inset;
	}
	
	public static InsetBorders drawInsetBorder(
			ShapeDrawable inset, 
			InsetBounds bounds) {
		
		// Border with and the paths for it...or something
		float borderWidth = 1.25f;
		Path left = new Path();
		Path right = new Path();
		Path top = new Path();
		Path bottom = new Path();
		
		left.moveTo(0, bounds.height);
		left.lineTo(0, 0);
		left.lineTo(borderWidth, 0);
		left.lineTo(borderWidth, bounds.height);
		left.close();
		
		top.moveTo(0, 0);
		top.lineTo(bounds.width, 0);
		top.lineTo(bounds.width, borderWidth);
		top.lineTo(0,borderWidth);
		top.close();
		
		right.moveTo(bounds.width,bounds.height);
		right.lineTo(bounds.width, 0);
		right.lineTo(bounds.width - borderWidth, 0);
		right.lineTo(bounds.width - borderWidth, bounds.height);
		right.close();
		
		bottom.moveTo(0, inset.getBounds().height());
		bottom.lineTo(bounds.width,inset.getBounds().height());
		bottom.lineTo(bounds.width,inset.getBounds().height() - borderWidth);
		bottom.lineTo(0, inset.getBounds().height() - borderWidth);
		bottom.close();
		
		
		ShapeDrawable insetLeft = new ShapeDrawable(new PathShape(left,bounds.width, bounds.height));
		ShapeDrawable insetTop = new ShapeDrawable(new PathShape(top, bounds.width, bounds.height));
		ShapeDrawable insetRight = new ShapeDrawable(new PathShape(right, bounds.width, bounds.height));
		ShapeDrawable insetBottom = new ShapeDrawable(new PathShape(bottom, bounds.width, bounds.height));
		
		Rect b = inset.copyBounds();
		
		insetBottom.getPaint().setColor(0x88FEFEFE);
		insetBottom.setBounds(b);
		
		insetRight.getPaint().setColor(0x88FEFEFE);
		insetRight.setBounds(b);
		
		insetTop.getPaint().setColor(0x88373737);
		insetTop.setBounds(b);
		
		insetLeft.getPaint().setColor(0x88373737);
		insetLeft.setBounds(b);
		
		InsetBorders insetBorders = new InsetBorders();
		insetBorders.setInsetBottom(insetBottom);
		insetBorders.setInsetLeft(insetLeft);
		insetBorders.setInsetRight(insetRight);
		insetBorders.setInsetTop(insetTop);
				
		return insetBorders;
		
	}

}

package Core;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import Core.Constants.Dimensions;
import Core.Constants.Global;
import Core.Constants.ManagerManager;
import Managers.FontManager;
import Objects.SoundTrack;

public class Functions {
	public static String getFilenameNoExtension(String filename) {
		int i = filename.lastIndexOf('.');
		return filename.substring(0, i);
	}
	
	public static String getExtension(String filepath) {
		String extension = "";
		int i = filepath.lastIndexOf('.');
		if (i > 0)
		    extension = filepath.substring(i+1);
		return extension;
	}
	
	public static int getFontWidth(Graphics2D g, String str, float size) {
		return g.getFontMetrics(FontManager.getFont(size)).stringWidth(str);
	}
	
	public static BufferedImage rotateBufferdImage(BufferedImage bi, double angle) {
		double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = bi.getWidth();
        int h = bi.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(bi, null, 0, 0);
        g2d.dispose();

        return rotated;
	}
	
	public static long getElapsedTime() {
		return System.currentTimeMillis() - Global.startFlag;
	}
	public static BufferedImage dye(BufferedImage image, Color c)
    {
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, null,0,0);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(c);
        g.fillRect(0,0,w,h);
        g.dispose();
        return dyed;
    }
    
	public static int xAxisLoopCoord(int width, int period, int curOffset) {
		int addition = 0;
		int pos = curOffset;
		while((pos+addition)<0)
			addition += period;
		
		return curOffset + addition - width;
	}
	
	public static Color getDarkerColor(Color c, float f) {
		return new Color((int)(f*c.getRed()), (int)(f*c.getGreen()), (int)(f*c.getBlue()), c.getAlpha());
	}
	
	public static void drawFancyString(Graphics2D g, String str, int x, int y, float size, boolean isGradient) {
		Font f = FontManager.getFont(size);
		TextLayout tl = new TextLayout(str, f, g.getFontRenderContext());
		AffineTransform transform = g.getTransform();
		Shape outline = tl.getOutline(null);
		Rectangle bound = outline.getBounds();
		AffineTransform orig = g.getTransform();
		transform.translate(x, y);

		g.setTransform(transform);
		g.setColor(Color.WHITE);
		GradientPaint gp = new GradientPaint(x, y, new Color(255, 170, 17), x, (int)(y-size), Color.WHITE, true);         
		if(isGradient)
			g.setPaint(gp);
		g.fill(outline);
		g.setStroke(new BasicStroke(size/25));
		g.setColor(Color.BLACK);
		g.draw(outline);
		
		g.setTransform(orig);
	}
	
	public static void drawFancyString(Graphics2D g, String str, int x, int y, float size, Color internalColor) {
		if(str.length()==0)return;
		AffineTransform orig = g.getTransform();
		Font f = FontManager.getFont(size);
		TextLayout tl = new TextLayout(str, f, g.getFontRenderContext());
		AffineTransform transform = new AffineTransform();
		FontMetrics fm = g.getFontMetrics(f);
		Shape outline = tl.getOutline(null);
		Rectangle bound = outline.getBounds();
		transform = g.getTransform();
		transform.translate(x, y+fm.getAscent());
		
		g.setTransform(transform);
		g.setColor(internalColor);
		g.fill(outline);
		g.setStroke(new BasicStroke(size/25));
		g.setColor(Color.BLACK);
		g.draw(outline);
		
		g.setTransform(orig);
	}
	
	public static void setFont(Graphics2D g, float size) {
		g.setFont(FontManager.getFont(size));
	}
	
	public static void drawImage(Graphics2D g, int flag, int x, int y) {
		g.drawImage(ManagerManager.im.imageBundle.get(flag), null, x, y);
	}
	
	public static void drawImage(Graphics2D g, BufferedImage bi, int x, int y) {
		g.drawImage(bi, null, x, y);
	}
	
	public static void drawResizedImage(Graphics2D g, int flag, int x, int y, int w, int h) {
		BufferedImage resized = resizeImage(flag, w, h);
		g.drawImage(resized, null, x, y);
	}
	
	public static BufferedImage getImage(int flag) {
		return ManagerManager.im.imageBundle.get(flag);
	}
	
	public static BufferedImage resizeImage(int flag, int x, int y) {	//transformed size
		BufferedImage resized = null, bi = ManagerManager.im.imageBundle.get(flag);
		int ox = bi.getWidth();
		int oy = bi.getHeight();
		if(x==0) {
			if(y==0) return bi;
			int rx = (int)((double)(ox*y)/(double)oy);
			resized = new BufferedImage(rx,y,BufferedImage.TYPE_INT_ARGB);
			resized.getGraphics().drawImage(bi.getScaledInstance(rx, y, Image.SCALE_AREA_AVERAGING),0,0,rx,y,null);
		}else if(y==0) {
			int ry = (int)((double)(oy*x)/(double)ox);
			resized = new BufferedImage(x,ry,BufferedImage.TYPE_INT_ARGB);
			resized.getGraphics().drawImage(bi.getScaledInstance(x, ry, Image.SCALE_AREA_AVERAGING),0,0,x,ry,null);
		}else {
			resized = new BufferedImage(x,y,BufferedImage.TYPE_INT_ARGB);
			resized.getGraphics().drawImage(bi.getScaledInstance(x, y, Image.SCALE_AREA_AVERAGING),0,0,x,y,null);
		}
		return resized;
	}
	
	public static BufferedImage resizeImage(BufferedImage bi, int x, int y) {
		BufferedImage resized = null;
		int ox = bi.getWidth();
		int oy = bi.getHeight();
		if(x==0) {
			if(y==0) return bi;
			int rx = (int)((double)(ox*y)/(double)oy);
			resized = new BufferedImage(rx,y,BufferedImage.TYPE_INT_ARGB);
			resized.getGraphics().drawImage(bi.getScaledInstance(rx, y, Image.SCALE_AREA_AVERAGING),0,0,rx,y,null);
		}else if(y==0) {
			int ry = (int)((double)(oy*x)/(double)ox);
			resized = new BufferedImage(x,ry,BufferedImage.TYPE_INT_ARGB);
			resized.getGraphics().drawImage(bi.getScaledInstance(x, ry, Image.SCALE_AREA_AVERAGING),0,0,x,ry,null);
		}else {
			resized = new BufferedImage(x,y,BufferedImage.TYPE_INT_ARGB);
			resized.getGraphics().drawImage(bi.getScaledInstance(x, y, Image.SCALE_AREA_AVERAGING),0,0,x,y,null);
		}
		return resized;
	}

	public static void playSoundTrack(int key) {
		ManagerManager.sm.playSoundTrack(key);
	}
	
	public static void stopCurrentPlayingSoundTrack() {
		ManagerManager.sm.stopSoundTrack();
	}
	
	public static Color getRainbowColor(long offset, long cur, double speed) {
		long flag = (long)(cur*speed) + offset;
		final long total = 600;
		flag %= total;
		
		int r = getColorBound((flag<total/2)?510-2.55*flag:-255*4+2.55*flag);
		int g = getColorBound((flag<total/3)?2.55*flag:1020-2.55*flag);
		int b = getColorBound((flag<(2*total/3))?-510+2.55*flag:6*255-2.55*flag);
		
		return new Color(r,g,b);
	}
	
	public static int getColorBound(double flag) {
		if(flag>=256)flag = 255;
		if(flag<0)flag = 0;
		return (int)flag;
	}
	
	public static void smoothRendering(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON); //text smooth
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //roundRect smooth
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
	}
	
	public static void setInitialFrameBounds(JFrame frame) {
		Dimension screenResol = Dimensions.getScreenResolution();
		frame.setSize(Dimensions.DEFAULT_WINDOW_SIZE);
		frame.setLocation(
				screenResol.width/2 - frame.getSize().width/2,
				screenResol.height/2 - frame.getSize().height/2);
	}
}
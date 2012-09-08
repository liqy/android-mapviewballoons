/**
 * Copyright (c) 2011 readyState Software Ltd
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */

package mapviewballoons.example.custom;

import java.util.List;

import mapviewballoons.example.R;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.GeoPoint;
import com.baidu.mapapi.MapActivity;
import com.baidu.mapapi.MapController;
import com.baidu.mapapi.MapView;
import com.baidu.mapapi.Overlay;

public class CustomMap extends MapActivity {

	BMapManager mBMapMan = null;
	MapView mapView;
	List<Overlay> mapOverlays;
	Drawable drawable;
	Drawable drawable2;
	CustomItemizedOverlay<CustomOverlayItem> itemizedOverlay;
	CustomItemizedOverlay<CustomOverlayItem> itemizedOverlay2;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mBMapMan = new BMapManager(getApplication());
		mBMapMan.init("C852FAE280E276186DE82EB547721866666283B0", null);
		super.initMapActivity(mBMapMan);

		mapView = (MapView) findViewById(R.id.mapview);
		mapView.setBuiltInZoomControls(true);

		mapOverlays = mapView.getOverlays();

		// first overlay
		drawable = getResources().getDrawable(R.drawable.marker);
		itemizedOverlay = new CustomItemizedOverlay<CustomOverlayItem>(
				drawable, mapView);

		GeoPoint point = new GeoPoint((int) (39.90923 * 1E6),
				(int) (116.397428 * 1E6));
		CustomOverlayItem overlayItem = new CustomOverlayItem(
				point,
				"Tomorrow Never Dies (1997)",
				"(M gives Bond his mission in Daimler car)",
				"http://ia.media-imdb.com/images/M/MV5BMTM1MTk2ODQxNV5BMl5BanBnXkFtZTcwOTY5MDg0NA@@._V1._SX40_CR0,0,40,54_.jpg");
		itemizedOverlay.addOverlay(overlayItem);

		GeoPoint point2 = new GeoPoint((int) (39.9022 * 1E6),
				(int) (116.3922 * 1E6));
		CustomOverlayItem overlayItem2 = new CustomOverlayItem(
				point2,
				"GoldenEye (1995)",
				"(Interiors Russian defence ministry council chambers in St Petersburg)",
				"http://ia.media-imdb.com/images/M/MV5BMzk2OTg4MTk1NF5BMl5BanBnXkFtZTcwNjExNTgzNA@@._V1._SX40_CR0,0,40,54_.jpg");
		itemizedOverlay.addOverlay(overlayItem2);

		mapOverlays.add(itemizedOverlay);

		// second overlay
		drawable2 = getResources().getDrawable(R.drawable.marker2);
		itemizedOverlay2 = new CustomItemizedOverlay<CustomOverlayItem>(
				drawable2, mapView);

		GeoPoint point3 = new GeoPoint((int) (39.917723 * 1E6),
				(int) (116.3722 * 1E6));
		CustomOverlayItem overlayItem3 = new CustomOverlayItem(point3,
				"Sliding Doors (1998)", "(interiors)", null);
		itemizedOverlay2.addOverlay(overlayItem3);

		GeoPoint point4 = new GeoPoint((int) (51.51738 * 1E6),
				(int) (-0.08186 * 1E6));
		CustomOverlayItem overlayItem4 = new CustomOverlayItem(
				point4,
				"Mission: Impossible (1996)",
				"(Ethan & Jim cafe meeting)",
				"http://ia.media-imdb.com/images/M/MV5BMjAyNjk5Njk0MV5BMl5BanBnXkFtZTcwOTA4MjIyMQ@@._V1._SX40_CR0,0,40,54_.jpg");
		itemizedOverlay2.addOverlay(overlayItem4);

		mapOverlays.add(itemizedOverlay2);

		final MapController mc = mapView.getController();
		mc.animateTo(point2);
		mc.setZoom(16);

	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	@Override
	protected void onDestroy() {
		if (mBMapMan != null) {
			mBMapMan.destroy();
			mBMapMan = null;
		}
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		if (mBMapMan != null) {
			mBMapMan.stop();
		}
		super.onPause();
	}

	@Override
	protected void onResume() {
		if (mBMapMan != null) {
			mBMapMan.start();
		}
		super.onResume();
	}

}

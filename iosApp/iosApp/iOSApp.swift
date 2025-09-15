import SwiftUI
import shared

@main
struct iOSApp: App {
    init() {
        KoinKt.doInitKoin { koinApp in }
    }
	var body: some Scene {
		WindowGroup {
			ContentView()
		}
	}
}

import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var vm = UsersViewModel()
    var body: some View {
            NavigationView {
                Group {
                    if vm.users.isEmpty && vm.isLoading {
                        ProgressView().frame(maxWidth: .infinity, maxHeight: .infinity)
                    } else if let error = vm.error {
                        VStack(spacing: 12) {
                            Text(error).foregroundColor(.red)
                            Button("Retry") { vm.refresh() }
                        }
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                    } else {
                        List(vm.users, id: \.self) { anyUser in
                            UserRow(user: anyUser)
                        }
                        .listStyle(.plain)
                        .refreshable { vm.refresh() }
                    }
                }
                .navigationTitle("Users")
            }
            .task { vm.refresh() }
        }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

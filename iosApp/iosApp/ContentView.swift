import SwiftUI
import shared

struct ContentView: View {
    @StateObject private var vm = UsersViewModel()

    var body: some View {
        ZStack {
            List(vm.users, id: \.email) { u in
                HStack(alignment: .top, spacing: 12) {
                    AsyncImage(url: URL(string: u.image)) { phase in
                        switch phase {
                        case .empty:
                            ProgressView().frame(width: 56, height: 56)
                        case .success(let image):
                            image
                                .resizable()
                                .scaledToFill()
                                .frame(width: 56, height: 56)
                                .clipShape(Circle())
                                .background(Circle().fill(Color.secondary.opacity(0.15)))
                        case .failure:
                            Image(systemName: "person.crop.circle.fill")
                                .resizable()
                                .scaledToFit()
                                .frame(width: 56, height: 56)
                                .foregroundColor(.secondary)
                        @unknown default:
                            Color.clear.frame(width: 56, height: 56)
                        }
                    }

                    VStack(alignment: .leading, spacing: 0) {
                        Text("\(u.firstName) \(u.lastName)")
                            .font(.title3).fontWeight(.semibold)
                            .lineLimit(1).truncationMode(.tail)

                        Spacer().frame(height: 4)

                        Text("Birth: \(u.birthDate) (\(u.age) years old)")
                            .font(.subheadline)
                            .foregroundColor(.secondary)

                        Spacer().frame(height: 6)

                        HStack(alignment: .center, spacing: 8) {
                            Image(systemName: "envelope")
                                .font(.system(size: 16))
                                .foregroundColor(.secondary)
                            Text(u.email)
                                .font(.body)
                                .lineLimit(1)
                                .truncationMode(.middle)
                        }

                        HStack(alignment: .center, spacing: 8) {
                            Image(systemName: "phone")
                                .font(.system(size: 16))
                                .foregroundColor(.secondary)
                            Text(u.phone)
                                .font(.body)
                                .lineLimit(1)
                                .truncationMode(.middle)
                        }
                    }
                    .frame(maxWidth: .infinity, alignment: .leading)
                }
                .padding(.vertical, 6)
            }

            if vm.isLoading {
                ProgressView()
                    .frame(maxWidth: .infinity, maxHeight: .infinity)
                    .background(Color.black.opacity(0.05))
            }
        }
        .task { vm.refresh() }
    }
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}

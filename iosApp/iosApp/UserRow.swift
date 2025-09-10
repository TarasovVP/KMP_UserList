//
// Created by Volodymyr Tarasov on 10.09.2025.
//

import Foundation
import shared
import SwiftUI

struct UserRow: View {
    let user: User

    var fullName: String {
        let first = user.value(forKey: "firstName") as? String ?? ""
        let last  = user.value(forKey: "lastName")  as? String ?? ""
        return "\(first) \(last)"
    }
    var email: String { user.value(forKey: "email") as? String ?? "" }
    var phone: String { user.value(forKey: "phone") as? String ?? "" }
    var imageUrl: URL? {
        if let s = user.value(forKey: "image") as? String { return URL(string: s) }
        return nil
    }
    var birth: String { user.value(forKey: "birthDate") as? String ?? "" }
    var ageText: String {
        if let age = user.value(forKey: "age") as? Int32 { return "\(Int(age)) years old" }
        if let age = user.value(forKey: "age") as? Int   { return "\(age) years old" }
        return ""
    }

    var body: some View {
        HStack(alignment: .top, spacing: 12) {
            AsyncImage(url: imageUrl) { phase in
                switch phase {
                case .empty:
                    Color.gray.opacity(0.2)
                case .success(let image):
                    image.resizable().scaledToFill()
                case .failure:
                    Color.gray.opacity(0.2)
                @unknown default:
                    Color.gray.opacity(0.2)
                }
            }
            .frame(width: 56, height: 56)
            .clipShape(Circle())

            VStack(alignment: .leading, spacing: 4) {
                Text(fullName).font(.headline).lineLimit(1)
                Text("Birth: \(birth) (\(ageText))")
                    .font(.subheadline)
                    .foregroundStyle(.secondary)

                HStack(spacing: 8) {
                    Image(systemName: "envelope")
                    Text(email).font(.subheadline)
                }
                HStack(spacing: 8) {
                    Image(systemName: "phone")
                    Text(phone).font(.subheadline)
                }
            }
            Spacer()
        }
        .padding(.vertical, 6)
    }
}
